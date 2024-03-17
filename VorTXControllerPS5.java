/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class VorTXControllerPS5 extends CommandPS5Controller {
	public Trigger circle, cross, square, triangle, l1, r1, create, options, l3, r3, l2, r2, ps, touchpad,
					povUp, povUpRight, povRight, povDownRight, povDown, povDownLeft, povLeft, povUpLeft;

	public VorTXControllerPS5(int port) {
		super(port);
        		
		cross = this.cross();
		circle = this.circle();
		square = this.square();
		triangle = this.triangle();
		l1 = this.L1();
		r1 = this.R1();
		create = this.create();
		options = this.options();
		l3 = this.L3();
		r3 = this.R3();
		l2 = this.L2();
		r2 = this.R2();
        ps = this.PS();
        touchpad = this.touchpad();

		
		povUp = this.povUp();
		povUpRight = this.povUpRight();
		povRight = this.povRight();
		povDownRight = this.povDownRight();
		povDown = this.povDown();
		povDownLeft = this.povDownLeft();
		povLeft = this.povLeft();
		povUpLeft = this.povUpLeft();
	}

	/**
	 * This method is to fix the flawed input of an PS4 controller. The input is a box,
	 * both X and Y axis can go from -1 to 1. However, since the controller build physically
	 * forces the joystick into a circle, its impossible to reach the the corner of the box.
	 * This method fixes that issue.
	 * 
	 * This was originally made for driving swerve, as you don't want to slow down when moving diagonally.
	 * 
	 * @param isLeft - Are you asking for the left joystick?
	 * @param isX - Are you asking for the X Axis?
	 * @return The value of whichever joystick and axis you asked for.
	 */
	public double joystickFix(boolean isLeft, boolean isX) {
		double root2 = Math.sqrt(2);
		double x = isLeft ? getTrueLeftX() : getTrueRightX();
		double y = isLeft ? getTrueLeftY() : getTrueRightY();
		double magnitude = Math.sqrt(x*x + y*y);
		double[] joystick = {
			Math.signum(x) * Math.min(Math.abs(x*root2), magnitude),
			Math.signum(y) * Math.min(Math.abs(y*root2), magnitude)
		};
		return isX ? joystick[0] : joystick[1];
	}

	/**
	 * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean) joystickFix(isLeft, isX)} method
	 * To get the true value of the joystick, please use the {@link #getTrueLeftX() getTrueLeftX()} method
	 */
	@Override
	public double getLeftX() {
		return joystickFix(true, true);
	}

	/**
	 * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean) joystickFix(isLeft, isX)} method
	 * To get the true value of the joystick, please use the {@link #getTrueLeftY() getTrueLeftY()} method
	 */
	@Override
	public double getLeftY() {
		return joystickFix(true, false);
	}

	/**
	 * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean) joystickFix(isLeft, isX)} method
	 * To get the true value of the joystick, please use the {@link #getTrueRightX() getTrueRightX()} method
	 */
	@Override
	public double getRightX() {
		return joystickFix(false, true);
	}

	/**
	 * This gets the fixed value of the joystick using our {@link #joystickFix(boolean, boolean) joystickFix(isLeft, isX)} method
	 * To get the true value of the joystick, please use the {@link #getTrueRightY() getTrueRightY()} method
	 */
	@Override
	public double getRightY() {
		return joystickFix(false, false);
	}

	public double getTrueLeftX() {
		return getHID().getLeftX();
	}

	public double getTrueLeftY() {
		return getHID().getLeftX();
	}

	public double getTrueRightX() {
		return getHID().getRightX();
	}

	public double getTrueRightY() {
		return getHID().getRightY();
	}
	
	public void setRumble(RumbleType rumble, double intensity) {
		getHID().setRumble(rumble, intensity);
	}
}