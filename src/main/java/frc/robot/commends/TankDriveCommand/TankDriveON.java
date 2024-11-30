
package frc.robot.commends.TankDriveCommand;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.DriveTank.DriveTank;

public class TankDriveON extends Command {

  public static DriveTank drive = DriveTank.getInstance();
  
  private Supplier<Double> leftSupplier;
  private Supplier<Double> rightSupplier;

  public TankDriveON(Supplier<Double> left, Supplier<Double> right) {
    addRequirements(drive);
    leftSupplier = left;
    rightSupplier = right;
    

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drive.setVoltageLeft(leftSupplier.get());
    drive.setVoltageRight(rightSupplier.get());
  }

  @Override
  public void end(boolean interrupted) {
    drive.setVoltageLeft(0);
    drive.setVoltageRight(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
