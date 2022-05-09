import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup
  error: string = ""

  constructor(private fb: FormBuilder, private router: Router, 
    private userService: UserService) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: '',
      password: ''
    })
  }

  login() {
    let user = this.loginForm.value['username']
    let pass = this.loginForm.value['password']
    if((user != null && user.length >= 3) && (pass != null && pass.length >= 5)){
      this.loginForm.reset()
      this.userService.setUser(user)
      this.router.navigate(['/scheduling'])
    } else {
      this.loginForm.reset()
      this.error = "Username or password is not long enough"
    }
  }
}
