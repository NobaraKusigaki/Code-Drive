package frc.robot.subsystems;


import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
double tt = LimelightHelpers.getTX("");

private final NetworkTable table;
private final NetworkTableEntry tx;
private final NetworkTableEntry ty;
private final NetworkTableEntry area;


    public LimelightSubsystem() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
         tx = table.getEntry("tx");
         ty = table.getEntry("ty");
         area = table.getEntry("ta");
         SmartUpdate();
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
    public void setPipe(int pipe){
        table.getEntry("pipeline").setNumber(pipe);
    }
    public boolean getIfLime(){
        return getArea() > 0;
    }

    public boolean tagDetected(){
        return getArea() > 0 && table.getEntry("pipeline")
                .getDouble(0) == 1;
    }

    public boolean tapeDetected(){
        return getArea() > 0 && table.getEntry("pipeline")
                .getDouble(0) == 0;
    }
    public double getDistAdjust(){
        return Constants.KpDist * getArea();

    }

    public void SmartUpdate(){
        SmartDashboard.putNumber("x", getX());
        SmartDashboard.putNumber("y", getY());
        SmartDashboard.putNumber("area", getArea());
    }
@Override
    public void periodic(){

}


}
//dont forget
// pipe: 0 - retroflexive tape
// pipe: 1 - apriltag
