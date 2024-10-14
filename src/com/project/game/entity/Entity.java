package com.project.game.entity;

public class Entity
{
	int positionX;
	int positionY;

	float speedX;
	float speedY;

	public Entity ()
	{
		System.out.println("Entity class Ok");
	}

	public void setPositionEntity(int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void getPositionEntity()
	{
		System.out.println("Entity Coord = " + this.positionX + " and" + this.positionY);
	}

	public void setSpeedEntity(float speedX, float speedY)
	{
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public void getSpeedEntity()
	{
		System.out.println("Entity Speed = " + this.speedX + " and " + this.speedY);
	}
}