import com.dagger.transfer.process.IGroovyEngine;

public class Script implements IGroovyEngine{

    public List<Boolean> excuseIntGroovy(List<Integer> srcDate, List<Integer> dstDate) {
    	def retResut = [];
    	def iRound = 0;
    	for (i in srcDate){
    		if (i == dstDate.get(iRound)) {
    		retResut.add(true);
    		} else{
    		retResut.add(false);
    		}
    		iRound++;
    	}
    	return retResut;
    }

        public List<Boolean> excuseListMapGroovy(List<Map<String, Object>> srcDate, List<Map<String, Object>> dstDate) {
    		return null;
    }
}