package com.qualcomm.ftcrobotcontroller.opmodes;
// the first thing that we need to do is import a couple of classes.
// the dc motor class will allow us to use the motors and all the functions related to them
// the op mode class will allow our programs to control the robot throuhg the ftc app
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Miguel on 9/10/2015.
 */
public class ExampleTeleOp extends OpMode{
    // dont worry about these red lines, they are just telling us that this program will not compile or work in its
    //      current form. once we add a couple more neccesary functions it should all iron out and the lines should go away
    // we're going to be programming a simple program for the push- bot,
    //      which has a drive motor on either side at the back, and another motor
    //      and two servos to control a hand and an arm at the top.
    //      we will only be using the drive motors for this sample program.
    // now we need to declare the motors as variables, the type is "dcmotor" and the names can be whatever you please
    //      but for the sake of clarity im going to go ahead and name these left and right  motor
    DcMotor rightMotor;
    DcMotor leftMotor;
    float speedR;
    float speedL;
    // after extending the OpMode to the class, a "public void init" is needed in order for the program to work with the android app.
    // while the init function does not neccesarily need to contain anything, it needs to be there.
        public void init()
        {// once we're inside the initialization function, it is a good idea to go ahead and get a few things cleared up, quite literally "initializing" our robot
            // for the sake of example we can go ahead and use telemetry to display data on the driver station phone
            telemetry.addData("1.", "Robot is Initializing!");
            // this statement tells the java program where to look for the motor that it needs to control.
            //    By setting leftMotor equal to  hardwareMap.dcMotor.get("motor_1"), we are telling the
            //    program to look for a motor named "motor_1" on the robot controller. this will be
            //    explained more when we set up the motors on the robot
            leftMotor = hardwareMap.dcMotor.get("leftMotor");
            // we then do the same for the right motor  with motor 2
            rightMotor = hardwareMap.dcMotor.get("rightMotor");
            // if you've built or programmed robots before, there is a good chance that you know that almost always
            // one of the motors ends up being reversed. in this case its the right motor so let me show you how to do that
            rightMotor.setDirection(DcMotor.Direction.REVERSE);

        }
    // the next necessary function will be our loop
    // this is where the bulk of our program will go
    //
        public void loop()
        {
             speedR = gamepad1.right_stick_y;
             speedL = gamepad1.left_stick_y;

            rightMotor.setPower(speedR);
            leftMotor.setPower(speedL);
            telemetry.addData("Right Motor Power = ", speedR);
            telemetry.addData("Left Motor Power = ", gamepad1.left_stick_y);
        }
}
