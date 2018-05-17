if [ $1==0 && $2==0 ];
		then echo "sahi se de chutiye : pathLocalToFile pathToRemoteLocation"
else 
	 scp  -r $1 $2
fi

java -cp ./sig-0.0.1-SNAPSHOT.jar pr.sig.Jugad $3 $4 $5




# /home/divay/Desktop/sig/msg.ser
# divay@localhost:/home/divay/Desktop

# ./encrypt.sh /home/divay/Desktop/alice divay@localhost:/home/divay/Desktop/Server ENCRYPT Thisisthemessage

# ./decrypt.sh divay@localhost:/home/divay/Desktop/Server/alice /home/divay/Desktop/bob/Connects/  DECRYPT /home/divay/Desktop/bob/Connects/alice/keyStore/alicekey.ser  /home/divay/Desktop/bob/Connects/alice/messageStore/alicemsg.ser
