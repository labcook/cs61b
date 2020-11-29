public class Planet  {
	public double xxPos;//Its current x position
	public double yyPos;//Its current y position
	public double xxVel;//Its current velocity in the x direction
	public double yyVel;//Its current velocity in the y direction
	public double mass;//Its mass
	public String imgFileName;//The name of the file that corresponds to the image that depicts the planet

	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP; yyPos = yP; xxVel = xV; yyVel = yP; mass = m; imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

}