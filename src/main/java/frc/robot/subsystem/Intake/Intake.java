
package frc.robot.subsystem.Intake;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ma5951.utils.DashBoard.MAShuffleboard;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Intake extends SubsystemBase {
  public static Intake intake;

  private MAShuffleboard shufflBord;

  private TalonFX motor;
  private TalonFXConfiguration motorCunfigo;
  private DigitalInput sensor;

  private StatusSignal<Current> currentDrwe;
  private StatusSignal<AngularVelocity> velocity;
  private StatusSignal<Temperature> motorTemp;
  private StatusSignal<Voltage> appliedVoltage;
  private StatusSignal<Angle> position;



  private Intake() {

    shufflBord = new MAShuffleboard("Intake");
    motor = new TalonFX(PortMap.Intake.MOTOR);
    sensor = new DigitalInput(PortMap.Intake.SENSOR);
    motorCunfigo = new TalonFXConfiguration();

    currentDrwe = motor.getSupplyCurrent();
    velocity =  motor.getVelocity();
    motorTemp = motor.getDeviceTemp();
    appliedVoltage = motor.getMotorVoltage();
    position = motor.getPosition();


    Cunfigo();
  }

  private void Cunfigo() {
    motorCunfigo.Feedback.RotorToSensorRatio = IntakeConstance.GEAR;

    motorCunfigo.CurrentLimits.SupplyCurrentLimitEnable = IntakeConstance.ENABLE_CURRENT_LIMIT;
    motorCunfigo.CurrentLimits.SupplyCurrentLimit = IntakeConstance.CURRENT_LIMIT;
    motorCunfigo.CurrentLimits.SupplyCurrentLowerLimit = IntakeConstance.CONTINOUS_POWER_LIMIT;
    motorCunfigo.CurrentLimits.SupplyCurrentLowerTime = IntakeConstance.CONTINOUS_POWER_DURATION;

    motorCunfigo.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motorCunfigo.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorCunfigo.Voltage.PeakForwardVoltage = 12;
    motorCunfigo.Voltage.PeakReverseVoltage = -12;
  }

  public void setVoltage(double Volt) {
    motor.setVoltage(Volt);
  }

  public boolean IsGamePeice() {
    return sensor.get();
  }

  public double getCurrentDrwe() {
    currentDrwe.refresh();
    return currentDrwe.getValueAsDouble();
  }


  public static Intake getInstance() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  @Override
  public void periodic() {

  }
}
