package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.applications.Calcs;

public class DriveSubsystem extends SubsystemBase {
    private Calcs calcs_driver;

    private final VictorSPX L1 = new VictorSPX(Constants.MOTOR_L1);
    private final VictorSPX L2 = new VictorSPX(Constants.MOTOR_L2);
    private final VictorSPX R1 = new VictorSPX(Constants.MOTOR_R1);
    private final VictorSPX R2 = new VictorSPX(Constants.MOTOR_R2);
    double [] motors;
    // public AHRS angular = new AHRS(SPI.Port.kMXP);


    public DriveSubsystem() {
        motorsInit();
    }

    public void driveCommand(double lt,double rt , double L_stickY, double L_stickX, double R_stickY,double R_stickX, double spd){
        calcs_driver = new Calcs(L_stickY,L_stickX,R_stickY,R_stickX,lt,rt);
        motors = calcs_driver.tank();
        motors[0]*=spd;
        motors[1]*= spd;
        minPower(motors[0], spd);
        minPower(motors[1],spd);
        setMotors(motors);


    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("M_right", motors[1]);
        SmartDashboard.putNumber("M_left",motors[0] );

    }

    public void setMotors(double[] motors){
        this.L1.set(ControlMode.PercentOutput, this.motors[0]);
        this.R1.set(ControlMode.PercentOutput, this.motors[1]);
    }

    public double minPower(double x, double y){
        if(Math.abs(x) > y){
            x = Math.copySign(y,x);
        } else if (Math.abs(x) < 0.04){
            return 0;
        }
        return x;
    }

    private void motorsInit(){
        this.L2.follow(L1);
        this.R2.follow(R1);
        this.L1.setInverted(false);
        this.R1.setInverted(true);
        this.L1.configNeutralDeadband(0.04);
        this.R1.configNeutralDeadband(0.04);
        this.L1.setNeutralMode(NeutralMode.Brake);
        this.R1.setNeutralMode(NeutralMode.Brake);


    }


}
