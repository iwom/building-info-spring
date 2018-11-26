import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RoomService} from './room.service';
import {MatTableDataSource} from '@angular/material';
import {Room} from '../room';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {
  rooms: MatTableDataSource<Room> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'detail'];

  constructor(
    private roomService: RoomService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getRooms();
  }

  getRooms(): void {
    this.roomService.getRooms(
      this.route.params['value'].id,
      this.route.params['value'].floorId
    ).subscribe(resp => {
      this.rooms.data = resp;
    });
  }

  goBack(): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId]);
  }

  onDetailClick(room: Room): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId, 'rooms', room.roomId]);
  }

}
