package frc.robot.subsystems;


import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase {
double tt = LimelightHelpers.getTX("");


private NetworkTable table;
private NetworkTableEntry tx;
private NetworkTableEntry ty;
private NetworkTableEntry area;

private GenericEntry shuffleTx;
private GenericEntry shuffleTy;
private GenericEntry shuffleA;


    public LimelightSubsystem() {
      table = NetworkTableInstance.getDefault().getTable("limelight");
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      area = table.getEntry("ta");

    shuffleTx = Shuffleboard.getTab("Lime Data")
            .add("Limelight X", 0.0)
            .withWidget("TextView")
            .getEntry();
    shuffleTy = Shuffleboard.getTab("Lime Data")
            .add("Limelight Y", 0.0)
            .withWidget("TextView")
            .getEntry();

    shuffleA = Shuffleboard.getTab("Lime Data")
            .add("Limelight Area", 0.0)
            .withWidget("TextView")
            .getEntry();

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
        return getArea() > 0 ? true : false;
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


@Override
    public void periodic(){
    shuffleTx.setDouble(getX());
    shuffleTy.setDouble(getY());
    shuffleA.setDouble(getArea());
}


}
//dont forget
// pipe: 0 - retroflexive tape
// pipe: 1 - apriltag
