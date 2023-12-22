import { Component } from '@angular/core';
import { RemoteService, userObj } from '../remote.service';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {
  remote:RemoteService;
  currentUser:CurrentUserService;
  username="";
  password="";
  constructor(remote:RemoteService,currentUser:CurrentUserService){
    this.remote=remote;
    this.currentUser=currentUser;
  }
  tryLogin(){
    if(this.username==""||this.password==""){alert("Please insert both the username and password to log in")}
    else{
      let check = this.remote.login(this.username,this.password)
      // console.log("check "+check)
      check.subscribe((data)=>console.log("log in subscribe "+JSON.parse(JSON.stringify(data.body))))
    }
    // check.subscribe((data:any)=>{
    //   let result:userObj;
    //   result=data;
    //   console.log(data);
    //   this.currentUser.setCurrentUser(result); 
    // })
  }
}
