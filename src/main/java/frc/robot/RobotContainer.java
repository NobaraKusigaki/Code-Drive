

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    private final Joystick joy = new Joystick(Constants.JOY_PORT);
    private final DriveSubsystem subDrive = new DriveSubsystem();
    private final LimelightSubsystem limesub = new LimelightSubsystem();
    private final AutoDriveCommand autodrive = new AutoDriveCommand(subDrive, limesub);

    public RobotContainer() {
        subDrive.setDefaultCommand(new DriveCommand(joy, subDrive));
        configureButtonBindings();
    }

    private void configureButtonBindings() {

    }

    public Command getAutonomousCommand() {
        return autodrive;
    }
}