package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.applications.Calcs;
import frc.robot.subsystems.DriveSubsystem;


public class DriveCommand extends Command {
    private final DriveSubsystem subDrive;
    private Joystick joy;

    private double L_stickY, L_stickX, R_stickY, R_stickX,lt, rt;
    private double spd=1;
    private int pov;
    boolean a,b,x;

    public DriveCommand(Joystick joy, DriveSubsystem subDrive) {
        this.subDrive = subDrive;
        this.joy = joy;
        addRequirements(subDrive);
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
    a = joy.getRawButton(Constants.BNT_A);
    b = joy.getRawButton(Constants.BNT_B);
    x = joy.getRawButton(Constants.BNT_X);
    this.R_stickX = joy.getRawAxis(Constants.RIGHT_X);
    this.R_stickY = joy.getRawAxis(Constants.RIGHT_Y);
    this.L_stickX = joy.getRawAxis(Constants.LEFT_X);
    this.L_stickY = joy.getRawAxis(Constants.LEFT_Y);
    this.lt = joy.getRawAxis(Constants.LT);
    this.rt = joy.getRawAxis(Constants.RT);
    this.pov = joy.getPOV();

    if(pov == -1){
    subDrive.driveCommand(R_stickX,R_stickY,L_stickX,L_stickY,rt,lt,spd);

    } else{
        subDrive.setMotors(Calcs.calcPov(pov,spd));
    }
        SmartDashboard.putBoolean("bnt B", b);
        SmartDashboard.putBoolean("bnt A", a);
        SmartDashboard.putBoolean("bnt X", x);
        SmartDashboard.putNumber("spd", spd);
        SmartDashboard.putNumber("LT", lt);
        SmartDashboard.putNumber("RT", rt);
    }

    @Override
    public boolean isFinished() {

        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    public void ifB(boolean a,boolean b, boolean x){
        if (b) {
            this.spd = 0.25;
        } else if (a) {
            this.spd = 0.5;
        } else if (x) {
            this.spd = 1;
        }

    }
}
