
package frc.robot.subsystem.Intake;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Temperature;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Intake extends SubsystemBase {
  public static Intake intake;

  private TalonFX motorL;
  private TalonFX motorR;
  private TalonFXConfiguration motorCunfig;

  private StatusSignal<Current> currentDrawMotorL;
  private StatusSignal<AngularVelocity> velocityMotorL;
  private StatusSignal<Temperature> motorTempMotorL;
  private StatusSignal<Voltage> appliedVoltageMotorL;
  private StatusSignal<Angle> positionMotorL;

  private StatusSignal<Current> currentDrawMotorR;
  private StatusSignal<AngularVelocity> velocityMotorR;
  private StatusSignal<Temperature> motorTempMotorR;
  private StatusSignal<Voltage> appliedVoltageMotorR;
  private StatusSignal<Angle> positionMotorR;

  private Intake() {

    motorL = new TalonFX(PortMap.Intake.INTAKE_MOTOR_L);
    motorR = new TalonFX(PortMap.Intake.INTAKE_MOTOR_R);

    currentDrawMotorL = motorL.getSupplyCurrent();
    velocityMotorL =  motorL.getVelocity();
    motorTempMotorL = motorL.getDeviceTemp();
    appliedVoltageMotorL = motorL.getMotorVoltage();
    positionMotorL = motorL.getPosition();

    currentDrawMotorR = motorR.getSupplyCurrent();
    velocityMotorR =  motorR.getVelocity();
    motorTempMotorR = motorR.getDeviceTemp();
    appliedVoltageMotorR = motorR.getMotorVoltage();
    positionMotorR = motorR.getPosition();
    motorCunfig = new TalonFXConfiguration();



    CunfigLeft();
    CunfigRight();
  }

  private void CunfigLeft() {
    motorCunfig.Feedback.RotorToSensorRatio = IntakeConstance.INTAKE_GEAR;

    motorCunfig.CurrentLimits.SupplyCurrentLimitEnable = IntakeConstance.ENABLE_CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLimit = IntakeConstance.CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerLimit = IntakeConstance.CONTINOUS_POWER_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerTime = IntakeConstance.CONTINOUS_POWER_DURATION;

    motorCunfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    motorCunfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorCunfig.Voltage.PeakForwardVoltage = 12;
    motorCunfig.Voltage.PeakReverseVoltage = -12;

    motorL.getConfigurator().apply(motorCunfig);
  }

  private void CunfigRight() {
    motorCunfig.Feedback.RotorToSensorRatio = IntakeConstance.INTAKE_GEAR;

    motorCunfig.CurrentLimits.SupplyCurrentLimitEnable = IntakeConstance.ENABLE_CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLimit = IntakeConstance.CURRENT_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerLimit = IntakeConstance.CONTINOUS_POWER_LIMIT;
    motorCunfig.CurrentLimits.SupplyCurrentLowerTime = IntakeConstance.CONTINOUS_POWER_DURATION;

    motorCunfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    motorCunfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    motorCunfig.Voltage.PeakForwardVoltage = 12;
    motorCunfig.Voltage.PeakReverseVoltage = -12;

    motorR.getConfigurator().apply(motorCunfig);
  }

  public void setInVoltage(double Volt) {
    motorL.setVoltage(Volt);
    motorR.setVoltage(Volt);
  }

  public double getLeftMotorCurrentDraw() {
    currentDrawMotorL.refresh();
    return currentDrawMotorL.getValueAsDouble();
  }


  public double getLeftMotorVelocity() {
    velocityMotorL.refresh();
    return velocityMotorL.getValueAsDouble();
  }


  public double getLeftMotorTemp() {
    motorTempMotorL.refresh();
    return motorTempMotorL.getValueAsDouble();
  }


  public double getLeftMotorAppliedVoltage() {
    appliedVoltageMotorL.refresh();
    return appliedVoltageMotorL.getValueAsDouble();
  }


  public double getLeftMotorPosition() {
    positionMotorL.refresh();
    return positionMotorL.getValueAsDouble();
  }

  public double getRightMotorCurrentDraw() {
    currentDrawMotorR.refresh();
    return currentDrawMotorR.getValueAsDouble();
  }


  public double getRightMotorVelocity() {
    velocityMotorR.refresh();
    return velocityMotorR.getValueAsDouble();
  }


  public double getRightMotorTemp() {
    motorTempMotorR.refresh();
    return motorTempMotorR.getValueAsDouble();
  }


  public double getRightMotorAppliedVoltage() {
    appliedVoltageMotorR.refresh();
    return appliedVoltageMotorR.getValueAsDouble();
  }


  public double getRightMotorPosition() {
    positionMotorR.refresh();
    return positionMotorR.getValueAsDouble();
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
