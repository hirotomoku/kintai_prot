package common;

public class WorkingState {
	public String InButtonState;
	public String OutButtonState;
	
	public WorkingState() {
		
	}
	
	public WorkingState(int resultCheckData, int resultTaikinData) {
		if(resultCheckData == 0)
			InButtonState = " ";
			OutButtonState = "disabled";
		if(resultCheckData == 1 && resultTaikinData == 0) {
			InButtonState = "disabled";
			OutButtonState = " ";
		}else if(resultCheckData == 1 && resultTaikinData == 1){
			InButtonState = "disabled";
			OutButtonState = "disabled";
		}
	}
	
}
