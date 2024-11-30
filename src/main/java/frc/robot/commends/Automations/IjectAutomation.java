
package frc.robot.commends.Automations;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystem.Shooter.Shooter;
import frc.robot.subsystem.Shooter.ShooterConstance;
import frc.robot.subsystem.Transfer.Transfer;
import frc.robot.subsystem.Transfer.TransferConstance;

public class IjectAutomation extends SequentialCommandGroup {
  public IjectAutomation() {
    addCommands(
      new InstantCommand(() -> Transfer.getInstance().setVoltage(TransferConstance.TRANSPORT_VOLTAGE_IN_INTAKE)),
      new InstantCommand(() ->  Shooter.getInstance().setVoltage(ShooterConstance.IJECT_VOLTAGE)),
      new WaitUntilCommand(() -> Transfer.getInstance().BallCounter()==0),
      new InstantCommand(() -> Transfer.getInstance().setVoltage(0)),
      new InstantCommand(() -> Transfer.getInstance().setVoltage(0))
    );
  }
}
