#!/bin/bash
currentYear=2014
positions=('QBs' 'RBs' 'WR_TEs')
pageFormat=('P' 'R' 'C')
cd /home/abatista/Development/Data/NFL/FootballDB
for i in $(seq 0 2)
do
	if [ ! -d ${positions[$i]} ];
	then
		mkdir ${positions[$i]}
	fi
	cd ${positions[$i]}
	pwd

	for year in $(seq 1997 $currentYear);
	do
		if [ ! -d "$year" ];
		then
			mkdir $year
		fi
		cd $year
		curl "http://www.footballdb.com/stats/stats.html?lg=NFL&yr=$year&mode=${pageFormat[$i]}&conf=&limit=100" > $(date +"%Y%m%d-%T").html
		cd ..
	done
	cd ..
done



