
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.commends.Automations.IjectAutomation;
import frc.robot.commends.Automations.IntakeAutomation;
import frc.robot.commends.Automations.ShootingAutimation;
import frc.robot.commends.IntakeCommand.IntakeIn;
import frc.robot.commends.IntakeCommand.IntakeOut;
import frc.robot.commends.ShooterCommand.IjectOn;
import frc.robot.commends.ShooterCommand.Shooting;



public class RobotContainer {

  public static CommandPS5Controller controller = new CommandPS5Controller(PortMap.RobotContainerPorts.ROBOT_CONTAINER_CONTROLLER);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    controller.R1().onTrue(new IntakeAutomation());
    controller.cross().whileTrue(new IjectAutomation());
    controller.L1().onTrue(new ShootingAutimation());

    controller.L2().whileTrue(new IntakeIn());
    controller.R2().whileTrue(new IntakeOut());

    controller.circle().whileTrue(new IjectOn());
    controller.create().whileTrue(new Shooting());

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
