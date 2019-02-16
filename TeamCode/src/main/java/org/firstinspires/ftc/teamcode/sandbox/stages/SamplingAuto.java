package org.firstinspires.ftc.teamcode.sandbox.stages;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.commands.MecanumDrive;
import org.firstinspires.ftc.teamcode.commands.TensorflowSampling;

@Autonomous(name="Sampling Auto",group="debug OpMode")

public class SamplingAuto extends LinearOpMode{

    private MecanumDrive mecanumDrive;
    private TensorflowSampling tensorflowSampling;

    private boolean isGold,seenGold;
    private int tries;



    @Override
    public void runOpMode() throws InterruptedException {
        tensorflowSampling = new TensorflowSampling(hardwareMap,telemetry);
        mecanumDrive = new MecanumDrive(hardwareMap);


        /** Wait for the game to begin */
        telemetry.addData(">", "Inited");
        telemetry.update();
        waitForStart();


        if(opModeIsActive()){
            //Start Tensorflow
            tensorflowSampling.activateTfod();

            mecanumDrive.stopMove();

            for(tries =0; tries < 3 && !seenGold ; ++tries){
                Thread.sleep(600);
                isGold = tensorflowSampling.checkGold();
                telemetry.addData("Gold detected?", isGold);
                telemetry.addData("Tries", tries);
                if(isGold && tensorflowSampling.getRecognitions() != null){
                    telemetry.addData("Gold seen?", seenGold);
                    tensorflowSampling.disableTfod();

                    if(tries ==2){//If mineral is in middle, push into the depo
                        mecanumDrive.moveFwd(2800);
                        while (mecanumDrive.isMotorBusy());
                        mecanumDrive.stopMove();

                        mecanumDrive.moveRvr(3000);
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
                    //Set seen gold
                    seenGold = isGold;
                    mecanumDrive.stopMove();
                }else if(tries > 0){//Not detected, Try Again
                    mecanumDrive.moveParaLeft(1800);
                    while (mecanumDrive.isMotorBusy());
                    mecanumDrive.stopMove();
                }
                telemetry.update();
            }

            tries -=2;//Reset counter
            telemetry.addData("Pushed, tries",tries);
            telemetry.update();

            ///Move to per. wall
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

            //Try to move forward to line up with depo
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

            telemetry.update();
        }
    }


}