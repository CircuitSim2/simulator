package gui;

import circuit.Element;

public class Susiki
{
	private MainDispApp disp;

	public Susiki (MainDispApp disp){
		this.disp = disp;
	}


	public void showElement()
	{
		int resi=0,capa=0,indu=0;

		//ElemType elemlist[] = new ElemType[disp.mainCircuit.getElem().length];


		for(int z =0; z < disp.mainCircuit.getElem().length; z++)
		{
			Element elem = disp.mainCircuit.getElem(z);
			//System.out.println(elem.getEt());
			switch(elem.getEt()){
				case RESISTANCE:
					resi++;
					break;
				case CAPACITANCE:
					capa++;
					break;
				case INDUCTANCE:
					indu++;
					break;
				case LINE:
					break;
				default:
					break;
			}

		}

		if((resi==1)&&(indu==1)&&(capa==0)){
			disp.formula = "RL直列回路に流れる電流の一般解\n"
					+ "i(t)=E/R-E/R・e^-(R/L)t \n\n"
					+ "抵抗Rの電圧の一般解\n"
					+ "eR(t)=E-E・e^-(R/L)t\n\n"
					+ "コイルLの電圧の一般解\n"
					+ "eL(t)=E・e^-(R/L)t";
		}
		else if((resi==1)&&(capa==1)&&(indu==0)){
			disp.formula = "コンデンサCの電荷の一般解\n"
					+ "q(t)=CE-CE・e^-(1/RC)t\n\n"
					+ "RC直列回路に流れる電流の一般解\n"
					+ "i(t)=(E/R)・e^-(1/RC)t\n\n"
					+ "抵抗Rの電圧の一般解\n"
					+ "eR(t)=E・e^-(1/RC)t\n\n"
					+ "コンデンサCの電圧の一般解\n"
					+ "eC(t)=E-E・e^-(1/RC)t";
		}
		else if((resi==1)&&(indu==1)&&(capa==1)){
			disp.formula = "合成インピーダンスz\n"
					+ "z=R+j(ωL-1/ωC)\n\n"
					+ "合成インピーダンスzの大きさ\n"
					+ "|z|=√(R^2+(ωL-1/ωC)^2)";
		}
		
		disp.textArea.setEditable(false);
		disp.textArea.setText(disp.formula);
	}
}
