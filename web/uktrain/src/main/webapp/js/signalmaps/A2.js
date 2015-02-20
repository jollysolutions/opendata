/*
 * Ashford A2 Signalling Diagram
 * 
 * Copyright 2015 Peter T Mount.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var SignalAreaMap = (function () {

    function SignalAreaMap() {
    }

    SignalAreaMap.width = 13;
    SignalAreaMap.height = 50;

    SignalAreaMap.plot = function (map) {

        // Down line
        var y1, y2, a;

        y1 = 1;
        y2 = y1 + 1;
        a = SignalMap.down([], 0, y1);
        a = SignalMap.up(a, 0, y2);
        a = SignalMap.down(a, 9, y1);
        a = SignalMap.up(a, 9, y2);
        a = SignalMap.line(a, 0, y1, 9, y1);
        a = SignalMap.line(a, 0, y2, 9, y2);
        
        // cranmore loop
        a = SignalMap.line(a,5,y1,5.25,y1-1);
        a = SignalMap.line(a,5.25,y1-1,7,y1-1);
        a = SignalMap.line(a,7,y1-1,7.25,y1);
        
        map.path(a);
        
        map.station(1,y1,'to Paddock Wood');
        map.station(-0.2,y1+1.3,'AD');
        
        map.berthr(1,y1,'0595');
        map.berthr(2,y1,'0597');
        map.berthr(3,y1,'0599');
        map.berthr(4,y1,'0601');
        
        map.station(6,y1-1,'Cranmore Loop');
        map.berthr(6,y1-1,'0603');
        map.berthr(6,y1,'0605');
        map.berthl(6,y2,'0598');

        map.berthr(8,y1,'0607');
        map.berthl(8,y2,'609X');
        
        map.station(9.2,y1+1.3,'A');
        
        // Headcorn
        y1 = y1+5;
        y2 = y1 + 1;
        a = SignalMap.down([], 0, y1);
        a = SignalMap.up(a, 0, y2);
        a = SignalMap.down(a, 10, y1);
        a = SignalMap.up(a, 10, y2);
        a = SignalMap.line(a, 0, y1, 10, y1);
        a = SignalMap.line(a, 0, y2, 10, y2);
        
        a = SignalMap.line(a,0.75,y2,1,y1);
        a = SignalMap.line(a,1.25,y1,1.5,y2);
        
        a = SignalMap.line(a,1.75,y1,2,y1-1);
        a = SignalMap.line(a,2,y1-1,4.75,y1-1);
        a = SignalMap.line(a,4.75,y1-1,5,y1);
        
        a = SignalMap.line(a,1.75,y2,2,y2+1);
        a = SignalMap.line(a,2,y2+1,4.75,y2+1);
        a = SignalMap.line(a,4.75,y2+1,5,y2);
        
        a = SignalMap.line(a,1.125,y2,1.625,y2+2);
        a = SignalMap.line(a,1.625,y2+2,5,y2+2);
        a = SignalMap.line(a,5,y2+2,5.5,y2);
        
        map.path(a);
        
        map.station(-0.2,y1+1.3,'A');
        
        map.station(3.5,y1-1,'Headcorn');
        map.platform(2.5,y1-1.5,2,'','2');
        map.platform(2.5,y2+1.5,2,'1','');
        map.berthl(3,y1-1,'0610');
        map.berthl(3,y1,'0608');
        map.berthl(3,y2,'0606');
        map.berthl(3,y2+1,'0604');
        map.berthl(3,y2+2,'0602');
        map.berthr(4,y1-1,'0611');
        map.berthr(4,y1,'0613');
        
        map.berthl(6.25,y1,'614X');
        map.berthl(6.25,y2,'0612');
        
        map.berthr(7.25,y1,'0615');
        map.berthl(7.25,y2,'0616');
        
        map.berthr(8.25,y1,'0617');
        map.berthl(8.25,y2,'0618');
        
        map.berthr(9.25,y1,'0619');
        map.berthl(9.25,y2,'0620');
        
        map.station(10.2,y1+1.3,'B');

        y1 = y1+5;
        y2 = y1 + 1;
        a = SignalMap.down([], 0, y1);
        a = SignalMap.up(a, 0, y2);
        a = SignalMap.down(a, 10, y1);
        a = SignalMap.up(a, 10, y2);
        a = SignalMap.line(a, 0, y1, 10, y1);
        a = SignalMap.line(a, 0, y2, 10, y2);
        
        map.path(a);
        
        map.station(-0.2,y1+1.3,'B');

        for(i=1;i<10;i++) {
            map.berthr(i,y1,'0'+(619+(2*i)));
            map.berthr(i,y2,'0'+(620+(2*i)));
        }
        
        map.station(6.5,y1,'Pluckley');
        map.platform(6.5,y1-0.5,1,'','2');
        map.platform(5.5,y2+0.5,1,'1','');
        
        map.station(10.2,y1+1.3,'C');

        y1 = y1+5;
        y2 = y1 + 1;
        a = SignalMap.down([], 0, y1);
        a = SignalMap.up(a, 0, y2);
        a = SignalMap.down(a, 11.75, y1);
        a = SignalMap.up(a, 11.75, y2);
        a = SignalMap.line(a, 0, y1, 11.75, y1);
        a = SignalMap.line(a, 0, y2, 11.75, y2);
        
        a = SignalMap.line(a, 8.75, y1, 9, y2);
        
        a = SignalMap.line(a, 9.25, y1, 9.5, y1-1);
        a = SignalMap.line(a, 9.5, y1-1, 11.75, y1-1);
        a = SignalMap.down(a,10,y1-1);
        a = SignalMap.down(a, 11.75, y1-1);
        
        a = SignalMap.line(a, 9.25, y2, 9.5, y2+1);
        a = SignalMap.line(a, 9.5, y2+1, 11.75, y2+1);
        a = SignalMap.down(a,11.75,y2+1);
        
        a = SignalMap.line(a, 5.75, y2, 6.25, y2+2);
        a = SignalMap.line(a, 6.25, y2+2, 10.9, y2+2);
        a = SignalMap.line(a, 10.9, y2+2, 11.15, y2+1);
        a = SignalMap.line(a, 11.325, y2+1, 11.625, y2);
        
        a = SignalMap.buffer(a,6.25,y2+3);
        a = SignalMap.line(a, 6.25, y2+3, 7.5, y2+3);
        a = SignalMap.line(a, 7.5, y2+3, 7.75, y2+2);

        a = SignalMap.line(a, 7.5, y2+4, 8, y2+2);
        
        a = SignalMap.line(a, 8.25, y2+2, 8.5, y2+3);
        a = SignalMap.line(a, 8.5, y2+3, 9.5, y2+3);
        a = SignalMap.buffer(a,9.5,y2+3);
        
        map.path(a);
        
        map.station(-0.2,y1+1.3,'C');

        for(i=1;i<4;i++) {
            map.berthr(i,y1,'0'+(637+(2*i)));
            map.berthr(i,y2,'0'+(638+(2*i)));
        }
        
        map.berthl(4,y2,'0646');
        map.berthr(5,y1,'0645');
        map.berthr(5,y2,'647X');
        
        map.berthl(6.5,y2,'0652');
        map.berthr(7.5,y1,'0649');
        map.berthr(7.5,y2,'651X');
        
        map.station(7.75,y2+2,'Depot Reception Line');
        map.berthl(7,y2+2,'0650');
        // unknown
        map.berth(6.75,y2+4,'2113');
        
        map.berthr(10.25,y1-1,'0653');
        map.berthr(10.25,y1,'0655');
        map.berthr(10.25,y2+1,'0659');
        map.berthr(10.25,y2+2,'0661');
        
        map.station(11.8,y1+1.3,'D');

        y1 = y1+7+5;
        y2 = y1 + 1;
        // to headcorn
        a = SignalMap.line([], 4, y1, 11.75, y1);
        a = SignalMap.line(a, 4, y2, 11.75, y2);
        a = SignalMap.line(a, 4, y2+1, 11.75, y2+1);
        a = SignalMap.line(a, 4, y2+2, 11.75, y2+2);
        
        // to Charing
        a = SignalMap.down(a, 0, y1-3);
        a = SignalMap.line(a, 0, y1-3, 5.75, y1-3);
        a = SignalMap.line(a, 5.75, y1-3, 6.5, y1);
        a = SignalMap.line(a, 1.75, y1-2, 2, y1-3);

        a = SignalMap.up(a, 0, y1-2);
        a = SignalMap.line(a, 0, y1-2, 5.5, y1-2);
        a = SignalMap.line(a, 5.5, y1-2, 6.25, y2);

        a = SignalMap.line(a, 2.25, y1-3, 2.5, y1-4);
        a = SignalMap.line(a, 2.5, y1-4, 6, y1-4);
        a = SignalMap.line(a, 6, y1-4, 7, y1);
        
        a = SignalMap.line(a, 2.75, y1-4, 3, y1-5);
        a = SignalMap.line(a, 3, y1-5, 6, y1-5);
        
        a = SignalMap.line(a, 6.75, y1, 7, y2);
        a = SignalMap.line(a, 6.25, y2+1, 6.5, y2);
        a = SignalMap.line(a, 7.25, y2, 7.5, y2+1);
        a = SignalMap.line(a, 7.5, y2, 7.75, y1);
        map.path(a);
        
        map.station(0,y1-3,'to Charing');
        map.station(-0.2,y1-1.7,'MA');
        map.berthr(1,y1-3,'0851');
        map.berthl(1,y1-2,'0850');
        map.berthr(1,y1-1,'2119');

        map.berthl(3.75,y1-4,'0854');
        map.berthl(3.75,y1-3,'0856');
        map.berthl(3.75,y1-2,'0858');
        map.berthr(4.75,y1-4,'0855');
        map.berthr(4.75,y1-3,'0853');
        
        // to headcorn
        map.station(2.5,y2+1.3,'to Headcorn');
        map.station(3.8,y2+1.3,'D');
        map.berthl(5,y2,'662X');
        map.berthl(5,y2+1,'0660');
        map.berthl(5,y2+2,'0658');
        
        // to CTRL
        map.station(8.5,y1-2,'to Ashford West Jn\n(CTRL)');
        map.berthr(8,y1-2,'455C');
        map.berthl(8,y1-1,'453C');
        
        map.berthl(9,y1-2,'318C');
        map.berthr(9,y1-1,'312C');
        
//        a = SignalMap.line([], 1.5, y1 - 1, 1.5, y1 + 0.5);
//        a = SignalMap.line(a, 1.5, y1 + 0.5, 4, y1 + 0.5);
//        a = SignalMap.line(a, 4, y1 + 0.5, 4, y2 + 1);
//        map.path(a).attr({
//            fill: '#f66',
//            stroke: '#f66',
//            'stroke-dasharray': '5,5'
//        });
//        map.station(1.5, y1-0.5, 'AD - TE').attr({
//            fill: '#f66'
//        });
    };

    return SignalAreaMap;
})();
