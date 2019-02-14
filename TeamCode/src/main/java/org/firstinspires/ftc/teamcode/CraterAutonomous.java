package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.RiserControl;
import org.firstinspires.ftc.teamcode.commands.TensorflowSampling;
import org.firstinspires.ftc.teamcode.commands.VuforiaNavigation;

@Autonomous(name="Crater Autonomous",group="final")
public class CraterAutonomous extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private TensorflowSampling tensorflowSampling;
    private VuforiaNavigation vuforiaNavigation;
    private RiserControl riserControl;


    private boolean isGold,seenGold;
    private int tries;



    @Override
    public void runOpMode() throws InterruptedException{
        tensorflowSampling = new TensorflowSampling(hardwareMap,telemetry);
        vuforiaNavigation = new VuforiaNavigation(hardwareMap,telemetry);
        mecanumDrive = new MecanumDrive(hardwareMap);
        riserControl = new RiserControl(hardwareMap);

        waitForStart();

        tensorflowSampling.activateTfod();

        ///////// LANDING //////
        riserControl.riseRiser();
        Thread.sleep(10000);
        riserControl.stopRiser();

        mecanumDrive.moveParaRight();
        Thread.sleep(1000);
        mecanumDrive.stopMove();

        mecanumDrive.moveFwd();
        Thread.sleep(1000);
        mecanumDrive.stopMove();

        mecanumDrive.moveParaRight();
        Thread.sleep(4000);
        mecanumDrive.stopMove();

        ///////// SAMPLING ///////

        mecanumDrive.stopMove();


        for(tries =0; tries < 3 && !seenGold ; tries++){
            Thread.sleep(250);
            isGold = tensorflowSampling.checkGold();
            telemetry.addData("Gold detected?", isGold);
            telemetry.addData("Tries", tries);
            if(isGold && tensorflowSampling.getRecognitions() != null){
                mecanumDrive.moveFwd();
                Thread.sleep(500);
                mecanumDrive.stopMove();
                seenGold = isGold;
                tensorflowSampling.disableTfod();
                mecanumDrive.moveRvr();
                Thread.sleep(500);
                mecanumDrive.stopMove();
            }else if(tries > 0){
                mecanumDrive.moveParaLeft();
                Thread.sleep(1000);
                mecanumDrive.stopMove();
            }
            telemetry.update();
        }

        if(tries == 1){
            mecanumDrive.moveParaLeft();
            Thread.sleep(6000);
            mecanumDrive.stopMove();
        }else if(tries == 2){
            mecanumDrive.moveParaLeft();
            Thread.sleep(4500);
            mecanumDrive.stopMove();
        }else{
            mecanumDrive.moveParaLeft();
            Thread.sleep(3000);
            mecanumDrive.stopMove();
        }

        mecanumDrive.rightConerning();
        Thread.sleep(1000);
        mecanumDrive.stopMove();


    }

}
