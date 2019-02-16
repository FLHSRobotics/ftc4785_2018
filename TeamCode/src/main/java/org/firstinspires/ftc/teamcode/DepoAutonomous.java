package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.MarkerServo;
import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.RiserControl;
import org.firstinspires.ftc.teamcode.commands.TensorflowSampling;
import org.firstinspires.ftc.teamcode.commands.VuforiaNavigation;

@Autonomous(name="Depo Autonomous",group="final")
public class DepoAutonomous extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private TensorflowSampling tensorflowSampling;
    private MarkerServo markerServo;
    private RiserControl riserControl;


    private boolean isGold,seenGold;
    private int tries;



    @Override
    public void runOpMode() throws InterruptedException{
        tensorflowSampling = new TensorflowSampling(hardwareMap,telemetry);
        markerServo = new MarkerServo(hardwareMap);
        mecanumDrive = new MecanumDrive(hardwareMap);
        riserControl = new RiserControl(hardwareMap);

        waitForStart();

        ///////// LANDING //////
        riserControl.riseRiser();
        Thread.sleep(7000);
        riserControl.stopRiser();

        mecanumDrive.moveParaRight(600);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        mecanumDrive.moveFwd(1200);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        mecanumDrive.moveParaRight(850);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        ////////// SAMPLE /////////////

        tensorflowSampling.activateTfod();

        mecanumDrive.stopMove();


        for(tries =0; tries < 3 && !seenGold ; ++tries){
            Thread.sleep(600);//Wait for object detection to kick in
            isGold = tensorflowSampling.checkGold();
            telemetry.addData("Gold detected?", isGold);
            telemetry.addData("Tries", tries);
            if(isGold && tensorflowSampling.getRecognitions() != null){
                telemetry.addData("Gold seen?", seenGold);
                tensorflowSampling.disableTfod();

                if(tries ==2){
                    mecanumDrive.moveFwd(2000);
                    while (mecanumDrive.isMotorBusy());
                    mecanumDrive.stopMove();

                    mecanumDrive.moveRvr(2400);
                    while (mecanumDrive.isMotorBusy());
                    mecanumDrive.stopMove();
                }else{
                    mecanumDrive.moveFwd(1000);
                    while (mecanumDrive.isMotorBusy());
                    mecanumDrive.stopMove();

                    mecanumDrive.moveRvr(1200);
                    while (mecanumDrive.isMotorBusy());
                    mecanumDrive.stopMove();
                }

                seenGold = isGold;
                mecanumDrive.stopMove();
            }else if(tries > 0){
                mecanumDrive.moveParaLeft(1800);
                while (mecanumDrive.isMotorBusy());
                mecanumDrive.stopMove();
            }
            telemetry.update();
        }
        tries -=2;//Reset counter
        telemetry.addData("Pushed, tries",tries);
        telemetry.update();

        if(tries == -1){
            mecanumDrive.moveParaLeft(4500);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();

        }else if(tries == 1){
            mecanumDrive.moveParaLeft(3400);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();

        }else if(tries == 2){
            mecanumDrive.moveParaLeft(2700);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();
        }

        mecanumDrive.turnAround(-940);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        if(tries == -1){
            mecanumDrive.moveFwd(600);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();
        }else if(tries == 1){
            mecanumDrive.moveFwd(300);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();
        }else if(tries == 2){
            mecanumDrive.moveFwd(300);
            while (mecanumDrive.isMotorBusy());
            mecanumDrive.stopMove();
        }

        mecanumDrive.moveParaRight(3000);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();

        /////// CLAIM //////
        markerServo.rotateServo(1);
        Thread.sleep(750);

        markerServo.rotateServo(0);
        Thread.sleep(750);

        markerServo.stopServo();

        /////// PARK ///////
        mecanumDrive.moveParaLeft(6500);
        while (mecanumDrive.isMotorBusy());
        mecanumDrive.stopMove();


    }

}
