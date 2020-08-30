const Discord = require('discord.js');
const net = require('net');

const CHANNELID = "557967903308382218"


const client = new Discord.Client()

var generalChannel;

var connection = false;
var othersock;

const server = net.createServer((socket) => {
  socket.on('data', (data) => {
    console.log(data.toString());
    generalChannel.send(data.toString());
  });
  socket.on('end', () =>{
    console.log('Lost connection with server');
  });
}).on('error', (err) => {
  console.error(err);
});
// Open server on port 9898
server.listen(3000, () => {
  console.log('opened server on', server.address().port);
});

server.on('connection', function(socket){
  connection = true;
  console.log('Connection established!');
  othersock = socket;
});

client.once('ready', () =>{
    console.log("I'm online!");
    generalChannel = client.channels.cache.get(CHANNELID);
});


client.on('message', message => {
    if(message.channel.name == "lekker_typen" && !message.author.bot){
        msg = '<' + message.author.username + '> ' + message.content;
        console.log(msg);
        if(connection){
          msg = msg.replace(/\n/g, " ");
          othersock.write(msg + '\n');
          console.log('Sent msg to ' + othersock.address().port);
        }
        //message.channel.send("stop es met dat gekuttest");
        
    }
});


client.login('NzQ3NDc3MDg5NDYwMDI3NTIy.X0PcSQ.mqS7Xr3WAeIIzFRURPlZsRCedas');


