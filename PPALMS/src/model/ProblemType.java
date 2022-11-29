package model;

/**
 * ProblemType 
 * 
 * @brief An enumerated type for indicating
 * the type of Parson's problem that a user can create
 * using the PPALMS system.
 * 
 * ProblemType is used in the PpalmsProblem,
 * which is stated in the PPALMS design document
 * and highlights its purpose respectively.
 * 
 * @author Anthony Narlock
 *
 */
public enum ProblemType {
	Ordering, FillInBlank, MultipleChoice;
}
