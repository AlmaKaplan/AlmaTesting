
package frc.robot.subsystem.Feeder;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Feeder extends SubsystemBase {
  public static Feeder feeder;

  private TalonFX motor;
  private DigitalInput sensor;
  
  public Feeder() {
    motor = new TalonFX(PortMap.Feeder.FEEDER_MOTOR);
    sensor = new DigitalInput(PortMap.Feeder.FEEDER_SENSOR);
  }

  @Override
  public void periodic() {
  }
}
