package br.fatec.classmate.models;

public class Classroom
{
    private int floor;
    private int room;

    public Classroom(int pFloor, int pRoom)
    {
        setFloor(pFloor);
        setRoom(pRoom);
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public int getRoom()
    {
        return room;
    }

    public void setRoom(int room)
    {
        this.room = room;
    }
}
