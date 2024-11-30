
package frc.robot.commends.Automations;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystem.Intake.Intake;
import frc.robot.subsystem.Intake.IntakeConstance;
import frc.robot.subsystem.Transfer.Transfer;
import frc.robot.subsystem.Transfer.TransferConstance;

public class IntakeAutomation extends SequentialCommandGroup {
  public IntakeAutomation() {
    addCommands(
      new InstantCommand(() ->  Intake.getInstance().setVoltage(IntakeConstance.INTAKE_IN_VOLTAGE)),
      new InstantCommand(() -> Transfer.getInstance().setVoltage(TransferConstance.TRANSPORT_VOLTAGE_IN_INTAKE)),
      new WaitUntilCommand(() -> Transfer.getInstance().BallCounter()==3),
      new InstantCommand(() ->  Intake.getInstance().setVoltage(0)),
      new InstantCommand(() -> Transfer.getInstance().setVoltage(0))
    );
  }
}
