import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // this.userService.findAll()
    //   .subscribe(res => {
    //     this.users = res.content;
    //   }, err => {
    //     console.error(err);
    //   });
  }

  delete(id: number | null) {

  }
}
