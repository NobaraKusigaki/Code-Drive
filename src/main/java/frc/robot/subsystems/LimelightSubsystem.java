package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
double tt = LimelightHelpers.getTX("");
private NetworkTable table;
private NetworkTableEntry tx;
private NetworkTableEntry ty;
private NetworkTableEntry area;


    public LimelightSubsystem() {
      table = NetworkTableInstance.getDefault().getTable("limelight");
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      area = table.getEntry("ta");
    }

    public double getX() {
        return tx.getDouble(0);
    }

    public double getY() {
        return ty.getDouble(0);
    }

    public double getArea() {
        return area.getDouble(0);
    }

@Override
    public void periodic(){
    SmartDashboard.putNumber("LimelightX", getX());
    SmartDashboard.putNumber("LimelightY", getY());
    SmartDashboard.putNumber("LimelightArea", getArea());
}


}

