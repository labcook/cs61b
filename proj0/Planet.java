public class Planet  {
	public double xxPos;//Its current x position
	public double yyPos;//Its current y position
	public double xxVel;//Its current velocity in the x direction
	public double yyVel;//Its current velocity in the y direction
	public double mass;//Its mass
	public String imgFileName;//The name of the file that corresponds to the image that depicts the planet
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP; yyPos = yP; xxVel = xV; yyVel = yV; mass = m; imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public boolean equal(Planet p){
		if((this.xxPos == p.xxPos)&&(this.yyPos == p.yyPos)&&(this.xxVel == p.xxVel)&&(this.yyVel == p.yyVel)&&(this.mass == p.mass)&&(this.imgFileName == p.imgFileName))
			return true;
		return false;
	} 

	public double calcDistance(Planet p){
		double dis = (this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos);
		return Math.sqrt(dis);
	}

	public double calcForceExertedBy(Planet p){
		return G*this.mass*p.mass/this.calcDistance(p)/this.calcDistance(p);
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);	
	}

	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] pp){
		double f = 0;
		for(int i = 0; i < pp.length; i++){
			if(this.equal(pp[i])) 
				continue;
			f += this.calcForceExertedByX(pp[i]);
		}
		return f;
	}

	public double calcNetForceExertedByY(Planet[] pp){
		double f = 0;
		for(int i = 0; i < pp.length; i++){
			if(this.equal(pp[i])) 
				continue;
			f += this.calcForceExertedByY(pp[i]);
		}
		return f;
	}

	public void update(double time,double xxforce,double yyforce){
		double xxa = xxforce/this.mass; 
		double yya = yyforce/this.mass; 
		this.xxVel += time*xxa; 
		this.yyVel += time*yya;
		this.xxPos +=time*this.xxVel; 
		this.yyPos +=time*this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos,yyPos, "./images/" + imgFileName);
	}

}