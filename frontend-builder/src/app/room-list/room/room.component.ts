import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from '../room.service';
import {Room} from '../../room';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  room: Room = new Room();
  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getRoom(
      this.route.params['value'].id,
      this.route.params['value'].floorId,
      this.route.params['value'].roomId
    );
  }

  getRoom(id: number, floorId: number, roomId: number): void {
    this.roomService.getRoom(id, floorId, roomId).subscribe(resp => {
      this.room = resp;
    });
  }

  goBack() {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId, 'rooms']);
  }

}
