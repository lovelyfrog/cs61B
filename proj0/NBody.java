import java.lang.reflect.Array;

public class NBody {

    public static double readRadius(String file) {
        In in = new In(file);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file) {
        In in = new In(file);
        int N = in.readInt();
        double radius = in.readDouble();
        Body[] allBodies = new Body[N];
        int i = 0;
        while (!in.isEmpty()) {
            if (i == N)
                break;
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allBodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            i += 1;
        }
        return allBodies;
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);

        /** Draw Background and One Body*/
//        StdDraw.enableDoubleBuffering();
//
//        StdDraw.setScale(-radius, radius);
//        StdDraw.clear();
//        StdDraw.picture(0, 0, "images/starfield.jpg");
//        allBodies[0].draw();
//        StdDraw.show();
//        StdDraw.pause(2000);

        double time = 0;
        while (time < T) {
            double[] xForces = new double[allBodies.length];
            double[] yForces = new double[allBodies.length];
            for (int i=0; i<allBodies.length; i++) {
                xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
                yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);
            }

            for (int i=0; i<allBodies.length; i++) {
                allBodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i=0; i<allBodies.length; i++) {
                allBodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", allBodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allBodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                    allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);
        }
    }
}
