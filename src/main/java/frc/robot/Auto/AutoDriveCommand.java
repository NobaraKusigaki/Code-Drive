package frc.robot.Auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class AutoDriveCommand extends Command {
    private final DriveSubsystem subdrive;
    private final LimelightSubsystem limesub;

    double distErr_tape;
    double distErr_tag;

    public AutoDriveCommand(DriveSubsystem subdrive, LimelightSubsystem limesub) {
        this.subdrive = subdrive;
        this.limesub = limesub;
        addRequirements(limesub,subdrive);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

  if (limesub.tapeDetected()){
    distErr_tape = limesub.getDistAdjust();

    if(distErr_tape < 0){
        subdrive.setMotors(new double[] {-0.40, -0.40});
    } else {
        subdrive.setMotors(new double[] {0.40, 0.40});
    }
        }
    if(limesub.tagDetected()){
        distErr_tag = limesub.getDistAdjust();

    if(limesub.getX() > 0){
        subdrive.setMotors(new double[] {0.40, -0.40});
    } else if(limesub.getX() < 0){
        subdrive.setMotors(new double[] {-0.40, 0.40});
    }
    if (distErr_tag > 80) {
            subdrive.setMotors(new double[]{0.40, 0.40});
        } else {
            subdrive.setMotors(new double[]{0.0, 0.0});
        }
    }

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        subdrive.setMotors(new double[]{0.0, 0.0});
    }
}
