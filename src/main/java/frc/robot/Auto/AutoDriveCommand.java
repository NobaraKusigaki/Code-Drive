package frc.robot.Auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class AutoDriveCommand extends Command {
    private final DriveSubsystem subdrive;
    private final LimelightSubsystem limesub;

    double distErro_tape;
    double distErro_tag;

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
    distErro_tape = limesub.getDistAdjust();
    //subdrive.

    } else if(limesub.tagDetected()){
        distErro_tag = limesub.getDistAdjust();
        //subdrive.
    }



    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
