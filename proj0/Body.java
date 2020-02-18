public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow(b.xxPos - xxPos, 2) + Math.pow(b.yyPos - yyPos, 2));
    }

    public double calcForceExertedBy(Body b) {
        double r = calcDistance(b);
        double F = G * mass * b.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double F = calcForceExertedBy(b);
        double Fx = F * (b.xxPos - xxPos) / calcDistance(b);
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double F = calcForceExertedBy(b);
        double Fy = F * (b.yyPos - yyPos) / calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys) {
        double Fx = 0;
        for (int i=0; i<allBodys.length; i++) {
            if (!this.equals(allBodys[i])) {
                Fx += calcForceExertedByX(allBodys[i]);
            }
        }
        return Fx;
    }

    public double calcNetForceExertedByY(Body[] allBodys) {
        double Fy = 0;
        for (int i=0; i<allBodys.length; i++) {
            if (!this.equals(allBodys[i])) {
                Fy += calcForceExertedByY(allBodys[i]);
            }
        }
        return Fy;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / mass;
        double ay = Fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
