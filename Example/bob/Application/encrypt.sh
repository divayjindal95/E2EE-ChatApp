java -cp ./sig-0.0.1-SNAPSHOT.jar pr.sig.Jugad $3 $4 
SENDERKEY="/keyStore/Mykey.ser"
SENDERMSG="/messageStore/msg.ser"
RECKEY="/keyStore/Mykey.ser"
RECMSG="/messageStore/msg.ser"
if [ $1==0 && $2==0 ];
		then echo "sahi se de chutiye : pathLocalToFile pathToRemoteLocation"
else 
	 scp  $1$SENDERKEY $2$RECKEY
	 scp  $1$SENDERMSG $2$RECMSG
fi

echo $1$SENDERKEY
# /home/divay/Desktop/sig/msg.ser
# divay@localhost:/home/divay/Desktop


