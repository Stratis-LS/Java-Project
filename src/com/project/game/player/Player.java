package com.project.game.player;

public class Player 
{
	int positionX;
	int positionY;

	float speedX;
	float speedY;
	float jump;

	int life;
	int strength;
	int stamina;


	public Player ()
	{
		System.out.println("Player class Ok");
	}

	public void setPositionPlayer(int positionX, int positionY)
	{
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void getPositionPlayer()
	{
		System.out.println("Player Coord = " + this.positionX + " and" + this.positionY);
	}

	public void setSpeedAndJumpPlayer(float speedX, float speedY, float jump)
	{
		this.speedX = speedX;
		this.speedY = speedY;
		this.jump = jump;
	}

	public void getSpeedAndJumpPlayer()
	{
		System.out.println("Player Speed = " + this.speedX + " and " + this.speedY);
		System.out.println("Player Jump = " + this.jump);
	}

	public void setStatsPlayer(int life, int strength, int stamina)
	{
		this.life = life;
		this.strength = strength;
		this.stamina = stamina;
	}

	public void getStatsPlayer()
	{
		System.out.println("Player Stats : Life = " + this.life + " - Strength = " + this.strength + " - Stamina = " + this.stamina);
	}
}