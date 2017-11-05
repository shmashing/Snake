public class SnakeSegment
{

  public int[] position = new int[2];
  public int[] directionVector = new int[2];
  public boolean isHead;
  public boolean collision = false;

  public void changeDirection(int[] newDirectionVector)
  {
    if(((newDirectionVector[0] + directionVector[0]) != 0) ||
       ((newDirectionVector[1] + directionVector[1]) != 0))
    {
      directionVector = newDirectionVector;
    }
  }


  public void move(int[] direction)
  {
    if(isHead)
    {
      position[0] += direction[0];
      position[1] += direction[1];

    }
  }

  public void follow(SnakeSegment segment)
  {
    position[0] = segment.position[0];
    position[1] = segment.position[1];  
  }

  public void draw(int[] xyCoords, int[] direction)
  { 
    if(isHead)
    {
      StdDraw.setPenColor(StdDraw.BLUE);
      if(direction[0] == 1)
      {
        StdDraw.line(xyCoords[0], xyCoords[1], xyCoords[0] + 1, xyCoords[1]);
      }
      else if(direction[0] == -1)
      {
        StdDraw.line(xyCoords[0], xyCoords[1], xyCoords[0] - 1, xyCoords[1]);
      }
      else if(direction[1] == 1)
      {
        StdDraw.line(xyCoords[0], xyCoords[1], xyCoords[0], xyCoords[1] + 1);
      }
      else if(direction[1] == -1)
      {
        StdDraw.line(xyCoords[0], xyCoords[1], xyCoords[0], xyCoords[1] - 1);
      }

    }
    else
    {
      StdDraw.setPenColor(StdDraw.BLACK);
    }

    StdDraw.filledCircle(xyCoords[0], xyCoords[1], 0.5);

    
  }

}
