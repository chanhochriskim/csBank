// dec 6 -- 9:10am ~ 9:20am
/*
(thanks, hint!)
- loop over, count how many R's, _'s and L's. 
- if R count == L count, return _ count
- if not, return _ count + which ever math.max(r, l) - math.min(r, l) <-- cuz we have to subtract from the opposite side.
*/

class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int lcount = 0;
        int rcount = 0;
        int _count = 0;

        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') {
                lcount++;
            } else if (moves.charAt(i) == 'R') {
                rcount++;
            } else {
                _count++;
            }
        }

        if (lcount == rcount) {
            return _count;
        } 

        return _count + Math.max(lcount, rcount) - Math.min(lcount, rcount);
    }
}
