package com.project.game.ennemy;

public class Ennemy
{
	int positionX;
	int positionY;

	float speedX;
	float speedY;

	int life;
	int strength;


	public Ennemy ()
	{
		System.out.println("Ennemy class Ok");
	}

	public void setPositionEnnemy(int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void getPositionEnnemy()
	{
		System.out.println("Ennemy Coord = " + this.positionX + " and" + this.positionY);
	}

	public void setSpeedEnnemy(float speedX, float speedY)
	{
		this.speedX = speedX;
		this.speedY = speedY;
	}

	public void getSpeedEnnemy()
	{
		System.out.println("Ennemy Speed = " + this.speedX + " and " + this.speedY);
	}

	public void setStatsPlayer(int life, int strength)
	{
		this.life = life;
		this.strength = strength;
	}

	public void getStatsPlayer()
	{
		System.out.println("Ennemy Stats : Life = " + this.life + " | Strength = " + this.strength);
	}
}