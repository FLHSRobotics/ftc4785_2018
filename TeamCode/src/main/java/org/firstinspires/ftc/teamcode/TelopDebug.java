package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="TelOp Debug", group="opMode")
public class TelopDebug extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftFrontDrive,rightFrontDrive,leftRearDrive,rightRearDrive;
    private double leftFrontPower,rightFrontPower,leftRearPower,rightRearPower;
    private int leftFrontPosition,rightFrontPosition,leftRearPosition,rightRearPosition;

    private DcMotor leftArmMotor,rightArmMotor;
    private CRServo armSlider;
    private double armPower = 0.5;

    private DcMotor rightRise,leftRise;
    private double risePower = 0.00;


    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        setupDrive();
        setupRiser();
        setupArms();
        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }


    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        loopDrive();
        loopRiser();
        loopArm();

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time :" + runtime.toString());
        telemetry.addData("Gamepad","LeftStick X(%.2f) LeftStick Y(%.2f) RightStick X(%.2f) RightStick Y(%.2f)",gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x,gamepad1.right_stick_y);
        telemetry.addData("Motors", "left front(%.2f), right front (%.2f), left rear (%.2f), right rear(%.2f)", leftFrontPower, rightFrontPower,leftRearPower,rightRearPower);
        telemetry.addData("Motor Encoder", "left front(%d), right front (%d), left rear (%d), right rear(%d)", leftFrontPosition, rightFrontPosition,leftRearPosition,rightRearPosition);
        telemetry.addData("Hook Power","Right Hook %.2f, Left Hook %.2f",risePower,risePower);
        telemetry.update();
    }

    @Override
    public void stop() {
            leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setupDrive(){
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "left_rear_drive");
        rightRearDrive = hardwareMap.get(DcMotor.class,"right_rear_drive");


        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    private void setupArms(){
        leftArmMotor = hardwareMap.get(DcMotor.class,"left_arm_motor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "right_arm_motor");
        armSlider = hardwareMap.get(CRServo.class, "arm_slider");

        leftArmMotor.setDirection(DcMotor.Direction.FORWARD);
        rightArmMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    private void setupRiser(){
        rightRise = hardwareMap.get(DcMotor.class,"right_rise");
        leftRise = hardwareMap.get(DcMotor.class,"left_rise");

        rightRise.setDirection(DcMotor.Direction.REVERSE);
        leftRise.setDirection(DcMotor.Direction.FORWARD);
    }


    private void loopDrive(){
        double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = -gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        leftFrontPower = v1;
        leftRearPower = v3;
        rightFrontPower = v2;
        rightRearPower = v4;


        leftFrontPosition = leftFrontDrive.getCurrentPosition();
        rightFrontPosition = rightFrontDrive.getCurrentPosition();
        leftRearPosition = leftRearDrive.getCurrentPosition();
        rightRearPosition = rightRearDrive.getCurrentPosition();

        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftRearDrive.setPower(leftRearPower);
        rightRearDrive.setPower(rightRearPower);
    }

    private void loopRiser(){
        if(gamepad1.right_bumper){
            risePower = 0.75;
        }else if(gamepad1.left_bumper){
            risePower = -0.75;
        }else{
            risePower = 0.000;
        }
        leftRise.setPower(risePower);
        rightRise.setPower(risePower);
    }

    private void loopArm(){
        if(gamepad1.dpad_up){
            armSlider.setPower(1);
        }else if(gamepad1.dpad_down){
            armSlider.setPower(0);
        }else{
            armSlider.setPower(0.5);
        }

        if(gamepad1.dpad_left){
            leftArmMotor.setPower(0.5);
            rightArmMotor.setPower(0.5);
        }else if(gamepad1.dpad_right){
            leftArmMotor.setPower(-0.5);
            rightArmMotor.setPower(-0.5);
        }else{
            leftArmMotor.setPower(0);
            rightArmMotor.setPower(0);
        }
    }


}
