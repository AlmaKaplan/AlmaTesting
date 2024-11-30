
package frc.robot.commends.TankDriveCommand;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystem.DriveTank.DriveTank;
import frc.robot.subsystem.DriveTank.DriveTankConstance;

public class DriveTankAutonomous extends Command {

  public static DriveTank drive = DriveTank.getInstance();

  public DriveTankAutonomous() {
    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drive.setVoltageLeft(DriveTankConstance.DRIVE_TANK_VOLTAGE_IN_AUTO);
    drive.setVoltageRight(DriveTankConstance.DRIVE_TANK_VOLTAGE_IN_AUTO);
  }

  @Override
  public void end(boolean interrupted) {
    drive.setVoltageLeft(DriveTankConstance.DRIVE_TANK_VOLTAGE_IN_AUTO);
    drive.setVoltageRight(DriveTankConstance.DRIVE_TANK_VOLTAGE_IN_AUTO);
  }

  @Override
  public boolean isFinished() {
    new WaitCommand(3);
    return true;
  }
}
