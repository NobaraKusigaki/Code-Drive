package frc.robot.applications;

public class Calcs {

    private double seno1 = 0;
    private double seno2 = 0;
    private double mag1 = 0;
    private double mag2 = 0;
    public double lt, rt;
    public double L_stickY, L_stickX, R_stickY, R_stickX;
    public double spd = 1;
    public int pov;


    public Calcs(double L_stickY, double L_stickX, double R_stickY, double R_stickX, double lt, double rt) {
        this.L_stickX = L_stickX;
        this.L_stickY = -L_stickY;
        this.lt = lt;
        this.rt = rt;
        this.R_stickX = R_stickX;
        this.R_stickY = -R_stickY;


    }
    public void analogicValues() {
        this.mag1 = Math.hypot(L_stickX, L_stickY);
        this.mag2 = Math.hypot(R_stickX, R_stickY);
        this.seno1 = mag1 > 0.04 ? L_stickY / mag1 : 0;
        this.seno2 = mag2 > 0.04 ? R_stickY / mag2 : 0;
        this.lt = -lt;
    }
public void activityAnalog(){
        if(mag1 <0.04){
            L_stickY = 0;
            L_stickX = 0;
            mag1 = 0;

        } else if(mag2 < 0.04){
            R_stickY = 0;
            R_stickX = 0;
            mag2 = 0;

        }
}

    public double[] tank() {
        analogicValues();
        activityAnalog();
        double[] motors = new double[2];
        if (lt != 0) {
            lt *= spd;
        }
        if (L_stickX >= 0.04) {
            motors[0] = lt * (1 - L_stickX);
            motors[1] = lt;
        } else if (L_stickX < 0.04) {
            motors[0] = lt;
            motors[1] = lt * (1 + L_stickX);
        }

        if (rt != 0) {
            rt *= spd;
        }
        if (R_stickX >= 0.04) {
            motors[0] = rt * spd;
            motors[1] = rt * (1 - R_stickX) * spd;
        }
        if (R_stickX < 0.04) {
            motors[0] = rt * (1 + R_stickX) * spd;
            motors[1] = rt * spd;
        }

         // left analogic
        //quad1. x> 0 e y>0
        if (L_stickX > 0 && L_stickY >= 0) {
            motors[0] = mag1;
            motors[1] = (2 * seno1 - 1) * mag1;
        }
        // quad2. x<0 e y>0
        if (L_stickX < 0 && L_stickY >= 0) {
            motors[0] = (2 * seno1 - 1) * mag1;
            motors[1] = mag1;
        }
        // quad3. x<0 e y<0
        if (L_stickX < 0 && L_stickY <= 0) {
            motors[0] = (2 * seno1 + 1) * mag1;
            motors[1] = -mag1;
        }
        // quad4. x>0 e y<0
        if (L_stickX > 0 && L_stickY <= 0) {
            motors[0] = -mag1;
            motors[1] = (2 * seno1 + 1) * mag1;
        }

    return motors;

    }

    public void Right_analogic() {
        double motors[] = new double[2];
        seno1 = L_stickY / mag1;
        //quad1. x>0 e y>0
        if (R_stickX > 0 && R_stickY > 0) {
            motors[0] = (-2 * seno2 + 1) * mag2;
            motors[1] = -mag2;
        }
        //quad2. x<0 e y>0
        if (R_stickX < 0 && R_stickY > 0) {
            motors[0] = -mag2;
            motors[1] = (-2 * seno2 + 1) * mag2;
        }
        //quad3. x<0 e y<0
        if (R_stickX < 0 && R_stickY < 0) {
            motors[0] = mag2;
            motors[1] = (-2 * seno2 - 1) * mag2;
        }
        //quad4. x>0 e y<0
        if (R_stickX > 0 && R_stickY < 0) {
            motors[0] = (-2 * seno2 - 1) * mag2;
            motors[1] = mag2;
        }

    }


    public static double[] calcPov ( int pov, double spd){
        double motors[] = new double[2];

        switch (pov) {
            case 0:
                motors[0] = 1;
                motors[1] = 1;
                break;

            case 45:
                motors[0] = 1;
                motors[1] = 0;
                break;

            case 90:
                motors[0] = 1;
                motors[1] = -1;
                break;

            case 135:
                motors[0] = -1;
                motors[1] = 0;
                break;

            case 180:
                motors[0] = -1;
                motors[1] = -1;
                break;

            case 225:
                motors[0] = 0;
                motors[1] = -1;
                break;

            case 270:
                motors[0] = -1;
                motors[1] = 1;
                break;

            case 315:
                motors[0] = 0;
                motors[1] = 1;
                break;

            default:
                motors[0] = 0;
                motors[1] = 0;

        }
        motors[0] *=spd;
        motors[1] *=spd;
        return motors;

    }
}