public class NBody{
	public static double readRadius(String s){
		In in = new In(s);
		int N = in.readInt();
		double r = in.readDouble();
		return r;
	}	

	public static Planet[] readPlanets(String s){
		In in = new In(s);
		int N = in.readInt();
		Planet p[] = new Planet[N];
		double r = in.readDouble();
		//first read the parameters, then use the instructor to make a planet.
		for(int i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			//the key step is to use the instructor to create a planet.
			p[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return p;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String file = args[2];
		double radius = readRadius(file);
		Planet[] planets = readPlanets(file);
		/* ready to draw **/
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		StdDraw.show();
		for(int i = 0; i < planets.length; i++){
			planets[i].draw();
		}
		StdDraw.enableDoubleBuffering();
		for (double time=0; time <= T; time+= dt) {
			/** Create an xForces array and yForces array to store data */
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			/** Calculate net x and net y and store them in arrays */
			for (int i=0; i<planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			/** Update acceleration,position */
			for (int i=0; i<planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			/** Draw the background image */
			StdDraw.picture(0, 0, "./images/starfield.jpg");

			/** Drawing planets */
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}

}