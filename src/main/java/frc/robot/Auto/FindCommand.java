package frc.robot.Auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class FindCommand extends Command {
   private DriveSubsystem subdrive;
   private LimelightSubsystem limesub;
   private boolean f = false;

    public FindCommand(DriveSubsystem subdrive, LimelightSubsystem limesub) {
        this.subdrive = subdrive;
        this.limesub = limesub;

        addRequirements(subdrive,limesub);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
       f = limesub.getIfLime();


    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return f;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
