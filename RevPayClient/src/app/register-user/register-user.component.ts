import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CurrentUserService } from '../current-user.service';
import { RemoteService, Obj, userObj } from '../remote.service';
@Component({
  selector: 'app-register-user',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.css'
})
export class RegisterUserComponent {
  username = ""
  email = ""
  phoneNumber= ""
  password= ""
  confirmPassword= ""
  httpClient:HttpClient;
  currentUser:CurrentUserService;
  remoteService:RemoteService;
  objects:Obj[];
  values="";
  constructor(httpClient:HttpClient, currentUser:CurrentUserService, remote:RemoteService){
    this.httpClient=httpClient;
    this.currentUser=currentUser;
    this.remoteService=remote;
    this.objects=[];
    this.values="";
  }
  
  OnClickSubmit(){
    // console.log("On click Username:" + this.username)
    // let response = this.httpClient.get("http://localhost:8080/users/username/testUser")
    // response.subscribe((data)=>console.log(data))
    //let userResponse: userObj;
   // userResponse = JSON.parse(JSON.stringify(response))
    //console.log("on click username from response: " + userResponse.username)
    //this.currentUser.setUsername(userResponse.username)
    let check = this.remoteService.get()
    let result:userObj;
    check.subscribe((data:any)=>{result=data
      console.log("subscribe "+result.username)
      this.currentUser.setCurrentUser(result)
    })
    //console.log(result.username);
    // this.remoteService.get().subscribe((results: Object)=>{this.objects.push(results as Obj)})
    // for(let obj of this.objects){
    //   this.values += obj.value+", "
    // }
    // console.log("values"+this.values);
  }
}
