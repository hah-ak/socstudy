import {io} from "socket.io-client";

// const socket = io('http://localhost:8086');

document.querySelector(".send").addEventListener('keydown',(e)=>{
    send(e);
})
document.querySelector(".normal").addEventListener('keydown',(e)=>{
    normal(e);
})

const send = async (e) => {
    if (e.keyCode == 13) {
        const data = e.currentTarget.value;
        const result = await fetch("/soc",{method:'POST',mode:'same-origin',headers:{'Content-Type':'application/Json'},body:JSON.stringify(data)})
        const res = result.json();
        console.log(res)
        socket.on("connect",()=>{
            socket.send(res)
        })
        return res;
    }
    return false;
}

const normal = async (e) => {
    if (e.keyCode == 16) {
        const data = e.currentTarget.value;
        const result = await fetch("/normal",{method:'POST',mode:'same-origin',headers:{'Content-Type':'application/Json'},body:JSON.stringify(data)})
        const res = result.json();
        console.log(res)
        return res;
    }
    return false;
}

// const eventSource = new EventSource('/test')
// eventSource.addEventListener("message",(evt) => {
//     console.log(evt)
// })

const socketUtil = () => {
    const socket = io('http://localhost:3000');
    const message = "";
    socket.emit('message',(e)=>{
        console.log(e)
    })
}
const aa = async () => {
    const result = await fetch("/mongotest",{method:'GET',mode:'same-origin',headers:{'Content-Type':'application/Json'}})
    return result.data;
}
const bb = async (data) => {
    const result = await fetch("/mongoinsert",{method:'POST',mode:'same-origin',headers:{'Content-Type':'text/plain'},body:JSON.stringify(data)})
    alert(result)
}
document.querySelector(".seemongolist").addEventListener("click",async ()=>{
    const result = await aa()
    document.querySelector(".mongolist").insertAdjacentText("beforeend",result)
})
document.querySelector(".mongoinsertbutton").addEventListener("click",()=>{
    const data = document.querySelector(".mongoinsert").value
    bb(data);
})