package model;

/**
 * LmsTarget is an enumerated type for indicating
 * the target LMS that a user can select using
 * the PPALMS system.
 * 
 * LmsTarget is used in the PpalmsProblem,
 * which is stated in the PPALMS design document
 * and highlights its purpose respectively.
 * 
 * @author Anthony Narlock
 *
 */
public enum LmsTarget {
	Canvas, D2L, Absorb, Matrix, Talent; 
}