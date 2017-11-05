public class Snake
{
  public static void placeFood(int[] position)
  {
    StdDraw.setPenColor(StdDraw.BLACK);

    StdDraw.line(position[0] - 0.5, position[1], position[0] + 0.5, position[1]);
    StdDraw.line(position[0], position[1] + 0.5, position[0], position[1] - 0.5);
    StdDraw.line(position[0] - .35, position[1] + 0.35, position[0] + 0.35, position[1] - 0.35);
    StdDraw.line(position[0] - .35, position[1] - 0.35, position[0] + 0.35, position[1] + 0.35);
  }

  public static SnakeSegment[] addNewSegment(SnakeSegment[] segments, int x, int y)
  {
    SnakeSegment[] copyArray = new SnakeSegment[segments.length+1];
    int newSize = segments.length+1;
    for(int j = 0; j < newSize - 1; j ++)
    {
      copyArray[j] = segments[j];
    }

    copyArray[newSize - 1] = new SnakeSegment();
    copyArray[newSize - 1].position[0] = x;
    copyArray[newSize - 1].position[1] = y;

    return(copyArray);
  }

  public static boolean checkForCollision(SnakeSegment[] segments)
  {
    for(int i = 1; i < segments.length; i ++)
    {
      if((segments[0].position[0] == segments[i].position[0]) && (segments[0].position[1] == segments[i].position[1]))
      {
        return (true);
      }
      if((Math.abs(segments[0].position[0]) == 28) || (Math.abs(segments[0].position[1]) == 28))
      {
        return (true);
      }
    }
    return(false);
  }

  public static void main(String[] args)
  {

    int[] RIGHT = {1, 0};
    int[] LEFT = {-1, 0};
    int[] UP = {0, 1};
    int[] DOWN = {0, -1};

    int gameSpeed = 200;
    int foodCount = 0;

    char userCommand = 'c';
    
    SnakeSegment[] snakeSegments = new SnakeSegment[4];

    for(int i = 0; i < snakeSegments.length; i ++)
    {
      snakeSegments[i] = new SnakeSegment();
    }
    
    snakeSegments[0].isHead = true;
    // snakeSegments[0].changeDirection(UP);

    for(int i = 0; i < snakeSegments.length; i ++)
    {
      snakeSegments[i].position[0] = 0;
      snakeSegments[i].position[1] = 0;
      snakeSegments[i].directionVector[0] = 0;
      snakeSegments[i].directionVector[1] = 0;
    }

    StdDraw.setScale(-35, 35);
    StdDraw.clear(StdDraw.BLACK);

    for(int i = 0; i < snakeSegments.length; i ++)
    {
      snakeSegments[i].draw(snakeSegments[i].position, snakeSegments[i].directionVector);
    }

    StdDraw.filledSquare(0, 0, 13);
    StdDraw.setPenColor(StdDraw.WHITE);

    int[] foodPosition = new int[2];

    for(int i = 0; i < 2; i ++)
    {
      foodPosition[i] = 27 - (int) Math.round(54 * Math.random());
    }
    while(true){
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.text(0,10,"Welcome!");
      StdDraw.text(0,5,"Use wasd to move the snake up, left, down,"); 
      StdDraw.text(0,0, "and right, respectively");
      StdDraw.text(0,-10, "press any key to play");
      StdDraw.show();

      if(StdDraw.hasNextKeyTyped()){
        break;
      }
    }

    while(true)
    {

      if(StdDraw.hasNextKeyTyped())
      {
        userCommand = StdDraw.nextKeyTyped();
      }

      if(userCommand == 'w')
      {
        snakeSegments[0].changeDirection(UP);
      }
      else if(userCommand == 'a')
      {
        snakeSegments[0].changeDirection(LEFT);
      }
      else if(userCommand == 's')
      {
        snakeSegments[0].changeDirection(DOWN);
      }
      else if(userCommand == 'd')
      {
        snakeSegments[0].changeDirection(RIGHT);
      }

      for(int i = (snakeSegments.length-1); i > 0; i --)
      {
        snakeSegments[i].follow(snakeSegments[i-1]); 
      }

      snakeSegments[0].move(snakeSegments[0].directionVector);

      StdDraw.clear(StdDraw.BLACK);
 
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledSquare(0, 0, 28);
      StdDraw.text(0,-32, "Score: " + foodCount);

      for(int i = 0; i < snakeSegments.length; i ++)
      {
        snakeSegments[i].draw(snakeSegments[i].position, snakeSegments[i].directionVector);
      }

      placeFood(foodPosition);

      StdDraw.show(gameSpeed);

      if(userCommand != 'c')
      {
        if((snakeSegments[0].position[0] != foodPosition[0]) && (snakeSegments[0].position[1] != foodPosition[1]))
        {
          if(checkForCollision(snakeSegments))
          {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(0,0,"GAME OVER");
            StdDraw.show();
            break;
          }
        }
      }


      if((snakeSegments[0].position[0] == foodPosition[0]) && (snakeSegments[0].position[1] == foodPosition[1]))
      {
        foodCount ++;
        snakeSegments = addNewSegment(snakeSegments, foodPosition[0], foodPosition[1]);

        for(int i = 0; i < 2; i ++)
        {
          foodPosition[i] = 27 - (int) Math.round(54 * Math.random());
        }

        if(gameSpeed > 140)
        {
          gameSpeed -= 15;
        }
        else if(gameSpeed > 90)
        {
          gameSpeed -= 10;
        }
        else if(gameSpeed > 50)
        {
          gameSpeed -= 5;
        }
        else if(gameSpeed > 10)
        {
          gameSpeed -= 1;
        }
      }
    }
  }
}
