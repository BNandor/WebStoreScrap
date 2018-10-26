cat $1 | grep 'product-new-price">.*[0-9][0-9]*<sup>' |sed 's/\&#46;//g'|sed 's/  *//g'| grep -o  ">[0-9][0-9]*<sup>" | sed 's/[^0-9]//g'
