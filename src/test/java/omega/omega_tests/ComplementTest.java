package omega.omega_tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;

import omega.automata.Automata;
import omega.automata.BA;
import omega.automata.NBA;
import omega.automata.State;
import omega.automata.TNBA;
import omega.automata.Transition;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.ncsb.ComplementNcsbOtf;
import omega.operations.complement.slice.ComplementSliceBreakpoint;
import omega.operations.complement.slice.ComplementTSliceBreakpoint;
import omega.operations.convert.NBA2TNBA;
import omega.operations.determinize.safra.DeterminizeTPiterman;
import omega.test.nbagen.NBAGen;
import omega.util.parser.AutomataPrinter;
import roll.words.Alphabet;

public class ComplementTest {
	
	public static void main(String []args) {
		
		while(true) {
//			NBA nba = NBAGen.getRandomNBA(4, 2);
			NBA nba = NBAGen.getRandomNBA(3, 2);
//			File fileP = new File("P.hoa");
//			try {
//	    		outputHOAStream1(nba, new PrintStream(new FileOutputStream(fileP)));
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
//			System.out.println(nba.toDot());
//			nba.getAcceptance().maximizeFinalSet();
//			File fileQ = new File("Q.hoa");
//			try {
//	    		outputHOAStream1(nba, new PrintStream(new FileOutputStream(fileQ)));
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
//			if(! spotEquiv(fileP.getAbsolutePath(), fileQ.getAbsolutePath())) {
//				System.out.println(nba.toDot());
//				break;
//			}
//			if(false)
//			{
				ComplementCommandOptions opts = new ComplementCommandOptions();
//				opts.lazyB = true;
//				opts.madjd = true;
//				opts.madjs = true;
//				NBA2TNBA tnba = new NBA2TNBA(nba);
//				tnba.explore();
//				ComplementTSliceBreakpoint complement = new ComplementTSliceBreakpoint(opts, tnba);
//				complement.explore();
				opts.lazyB = true;
				opts.lazyS = true;
//				ComplementNcsbOtf complement = new ComplementNcsbOtf(opts, nba);
				NBA2TNBA tnba = new NBA2TNBA(nba);
				tnba.explore();
				DeterminizeTPiterman tdet = new DeterminizeTPiterman(tnba);
				tdet.explore();
				if (! UtilTest.spotTestEquivalence(tnba, tdet)) {
					System.out.println(tnba.toDot());
					System.out.println(tdet.toDot());
					UtilTest.output(nba, System.out);
					break;
				}
//			}
		}
	}
    


}
