import com.dagger.transfer.process.IGroovyEngine;

public class FiledScritp implements IGroovyEngine{

    public List<Boolean> excuseIntGroovy(List<Integer> srcDate, List<Integer> dstDate) {
    	return null;
    }

    public List<Boolean> excuseListMapGroovy(List<Map<String, Object>> srcDate, List<Map<String, Object>> dstDate) {
    	   	def retResut = [];
	    	def iRound = 0;
	    	for (srcMap in srcDate){
	    		//只比较金额是否相等,因为金额是int类型，所以用==
	    		if (srcMap.get("pay_amount")*100 == dstDate.get(iRound).get("total_pay_fee")) {
	    		retResut.add(true);
	    		} else{
	    		retResut.add(false);
	    		}
	    		iRound++;
	    	}
		return retResut;
}
}